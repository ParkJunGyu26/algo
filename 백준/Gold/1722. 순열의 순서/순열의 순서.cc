#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, tmp;

bool cmp(int a, int b) {
	return a > b;
}

// N <= 20 -> 순열 직접 만드는 건 절대 불가능 20!
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	// long long aaaa = 1;
	// for (int i = 1; i < 21; i++) aaaa *= i;
	// cout << aaaa << "\n";

	cin >> n >> tmp;

	if (tmp == 1) {
		long long k;
		vector<int> ranK;
		vector<int> ans;
		cin >> k;
		k--;

		for (int i = 1; i <= n; i++) ranK.push_back(i);

		while (n--) {
			long long target = 1;
			
			for (int i = n; i > 0; i--) target *= i;
			long long share = k / target;
			long long remain = k % target;

			ans.push_back(ranK[share]);
			ranK.erase(ranK.begin() + share);
			k = remain;
		}

		for (int i = 0; i < ans.size(); i++) {
			cout << ans[i];
			if (i != ans.size()-1) cout << " ";
		}
	} else {
		vector<int> init(n);
		long long answer = 1;
		for (int i = 0; i < n; i++) cin >> init[i];

		for (int i = 0; i < n; i++) {
			long long cnt = 0;
			for (int j = i+1; j < n; j++) if (init[i] > init[j]) cnt++;

			long long left_len = 1;
			for (int j = 1; j < n-i; j++) left_len *= j;

			answer += (cnt * left_len);
		}

		cout << answer;
	}
}