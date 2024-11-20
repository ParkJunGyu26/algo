#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <queue>
#include <climits>
#include <stack>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int h, w, tmp, MAX, answer, minus;
	vector<int> v;
	vector<int> st;
	cin >> h >> w;

	answer = minus = 0;
	for (int i = 0; i < w; i++) {
		cin >> tmp;
		v.push_back(tmp);
		minus += tmp;
	}

	MAX = v[0];
	st.push_back(v[0]);
	for (int i = 1; i < w; i++) {
		if (MAX < v[i]) {
			while (!st.empty()) {
				answer += MAX;
				st.pop_back();
			}

			st.push_back(v[i]);
			MAX = v[i];
		} else {
			if (st.back() < v[i]) {
				int cnt = 0;
				while(!st.empty() && st.back() < v[i]) {
					st.pop_back();
					cnt++;
				}
				for (int j = 0; j < cnt; j++) {
					st.push_back(v[i]);
				}
			}
			st.push_back(v[i]);
		}
	}

	while (!st.empty()) {
		answer += st.back();
		st.pop_back();
	}

	cout << answer - minus;
}