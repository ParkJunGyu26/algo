#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int t, n, m;

// 누적합
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	unordered_map<int, long long> um_A;
	unordered_map<int, long long> um_B;

	cin >> t >> n;
	vector<int> A(n);
	for (int i = 0; i < n; i++) cin >> A[i];
	cin >> m;
	vector<int> B(m);
	for (int i = 0; i < m; i++) cin >> B[i];

	vector<int> prefix_A(n+1);
	vector<int> prefix_B(m+1);
	
	for (int i = 0; i < n; i++) prefix_A[i+1] = prefix_A[i] + A[i];
	for (int i = 0; i < m; i++) prefix_B[i+1] = prefix_B[i] + B[i];

	for (int i = 1; i < n+1; i++) {
		for (int j = i; j <= n; j++) {
			int target = prefix_A[j] - prefix_A[j-i];
			if (um_A.find(target) == um_A.end()) um_A[target] = 1;
			else um_A[target]++;
		}
	}

	for (int i = 1; i < m+1; i++) {
		for (int j = i; j <= m; j++) {
			int target = prefix_B[j] - prefix_B[j-i];
			if (um_B.find(target) == um_B.end()) um_B[target] = 1;
			else um_B[target]++;
		}
	}

	long long answer = 0;

	if (um_A.size() > um_B.size()) {
		for (auto B : um_B) {
			if (um_A.find(t-B.first) != um_A.end()) answer += (B.second * um_A[t-B.first]);
		}
	} else {
		for (auto A : um_A) {
			if (um_B.find(t-A.first) != um_B.end()) answer += (A.second * um_B[t-A.first]);
		}
	}

	cout << answer;
}