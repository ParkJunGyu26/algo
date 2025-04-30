#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

int m, n;
vector<vector<pair<int, int>>> vec;
vector<int> duplicate;

bool cmp(pair<int, int> a, pair<int, int> b) {
	return a.first >= b.first;
}

// 구성이 같은데 순서만 다른 우주의 쌍은 한 번만 센다. -> 인덱스 다시 고려하기
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> m >> n;
	vec.resize(m, vector<pair<int, int>>(n));
	duplicate.resize(m);

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			cin >> vec[i][j].first;
			vec[i][j].second = j;
		}
	}
	
	for (int i = 0; i < m; i++) sort(vec[i].begin(), vec[i].end(), cmp);

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n-1; j++) {
			if (vec[i][j].first == vec[i][j+1].first) {
				int target = max(vec[i][j].second, vec[i][j+1].second);
				vec[i][j].second = vec[i][j+1].second = target;
			}
		}
	}

	// O(M^2 * N) = 10^8
	int answer = 0;
	for (int i = 0; i < m; i++) {
		for (int j = i+1; j < m; j++) {
			bool check = true;
			for (int k = 0; k < n; k++) {
				if (vec[i][k].second != vec[j][k].second) check = false;

				if (!check) break;
			}

			if (check) answer++;
		}
	}

	// cout << "\n~~~\n";
	// for (int i = 0; i < m; i++) {
	// 	cout << "i : " << i << "\n";
	// 	for (int j = 0; j < n; j++) cout << vec[i][j].first << " " << vec[i][j].second << " / ";
	// 	cout << "\n----\n";
	// }

	int cnt = 1;
	for (int i = 0; i < m; i++) {
		if (duplicate[i] != 0) continue;
		duplicate[i] = cnt;
		for (int j = i+1; j < m; j++) {
			bool check = true;
			for (int k = 0; k < n; k++) {
				if (vec[i][k] != vec[j][k]) check = false;

				if (!check) break;
			}

			if (check) duplicate[j] = cnt;
		}

		cnt++;
	}

	for (int i = 0; i < m; i++) {
		int cntt = 0;
		for (int j = i+1; j < m; j++) {
			if (duplicate[i] == duplicate[j]) cntt++;
		}

		if (cntt > 1) answer -= cntt;
	}

	cout << answer;
}