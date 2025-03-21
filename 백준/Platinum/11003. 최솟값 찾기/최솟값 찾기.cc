#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int n, l;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> l;
	vector<int> vec(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	priority_queue<pair<int, int>> pq; // (값, 인덱스)

	for (int i = 0; i < n; i++) {
		pq.push({-vec[i], -i});
		cout << -pq.top().first << " ";
		while (pq.size() >= l && -pq.top().second <= i - l + 1) {
			pq.pop();
		}
	}
}