#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> A, answer, hubo;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	A.resize(n);
	hubo.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];

	answer.push_back(A[0]);
	hubo[0] = 0;
	for (int i = 1; i < n; i++) {
		if (answer.back() < A[i]) {
			answer.push_back(A[i]);
			hubo[i] = answer.size()-1;
		} else {
			int index = lower_bound(answer.begin(), answer.end(), A[i]) - answer.begin();
			answer[index] = A[i];
			hubo[i] = index;
		}
	}

	int target = answer.size()-1;
	for (int i = n-1; i >= 0; i--) {
		if (target == hubo[i]) {
			answer[hubo[i]] = A[i];
			target--;
		}

	}

	cout << answer.size() << "\n";
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i];
		if (i != answer.size()-1) cout << " ";
	}
}