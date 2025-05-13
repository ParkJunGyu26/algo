#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int t;

void solve(priority_queue<int>& pq, vector<int>& vec, int i, vector<int>& answer) {
	priority_queue<int> tmp = pq;

	int cnt = 0;
	int target;
	while(!tmp.empty() && cnt <= i/2) {
		target = tmp.top();
		tmp.pop();
		cnt++;
	}

	answer.push_back(target);
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> t;
	while(t--) { // O(10^3)
		int m;
		cin >> m;
		vector<int> vec(m), answer; // O(10^3 * 10^4 = 10^7)
		for (int i = 0; i < m; i++) cin >> vec[i];

		priority_queue<int> pq;
		for (int i = 0; i < m; i++) {
			pq.push(vec[i]);
			if (i % 2 == 0) solve(pq, vec, i, answer);
		}

		cout << answer.size() << "\n";
		for (int i = 0; i < answer.size(); i++) {
			if (i != 0 && i % 10 == 0) cout << "\n";
			cout << answer[i] << " ";
		}
		cout << "\n";
	}
}