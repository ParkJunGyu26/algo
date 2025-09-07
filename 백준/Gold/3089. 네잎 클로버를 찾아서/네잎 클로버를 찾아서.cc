#include <iostream>
#include <unordered_map>
#include <set>
#include <algorithm>

using namespace std;

int n, m;
unordered_map<int, set<int>> X, Y;
string order;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	// O (N log N)
	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;

		X[x].insert(y);
		Y[y].insert(x);
	}

	cin >> order;

	pair<int, int> dot = {0, 0}; // {x, y};
	// O(M)
	for (int i = 0; i < m; i++) {
        int nx = dot.first;
        int ny = dot.second;

        switch (order[i]) {
            case 'U': { // 같은 x에서 y 증가
                auto it = X[dot.first].upper_bound(dot.second);
                ny = *it;
                break;
            }
            case 'R': { // 같은 y에서 x 증가
                auto it = Y[dot.second].upper_bound(dot.first);
                nx = *it;
                break;
            }
            case 'D': { // 같은 x에서 y 감소
                auto it = X[dot.first].lower_bound(dot.second);
				--it;
				ny = *it;
                break;
            }
            case 'L': { // 같은 y에서 x 감소
                auto it = Y[dot.second].lower_bound(dot.first);
				--it;
				nx = *it;
                break;
            }
        }

		dot = {nx, ny};
	}

	cout << dot.first << " " << dot.second;
}