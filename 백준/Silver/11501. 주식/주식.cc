#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int t;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	while (t--) {
		int n; 
		cin >> n;
		vector<bool> visited(n);
		vector<int> vec(n);
		priority_queue<pair<int, int>> pq;
		for (int i = 0; i < n; i++) {
			cin >> vec[i];
			pq.push({vec[i], i});
		}

		long long answer = 0;
		int target = pq.top().first;
		int index = pq.top().second;
		for (int i = 0; i < n; i++) {
			while (i > index && target > vec[i]) {
				pq.pop();
				target = pq.top().first;
				index = pq.top().second;
			}

			if (vec[i] < target) answer += (target-vec[i]);
		}

		cout << answer << "\n";
	}
}