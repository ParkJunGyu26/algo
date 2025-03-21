#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<int> vec(n);
	unordered_map<long long, long long> um;
	for (int i = 0; i < n; i++) cin >> vec[i];

	long long answer = 0;
	vector<int> st;

	int index = 0;

	while (index < n) {

		while (!st.empty() && st.back() < vec[index]) {
			answer += um[st.back()];
			um.erase(st.back());
			st.pop_back();
		}

		if (!st.empty()) {
			if (um.find(vec[index]) == um.end()) answer++;
			else {
				int i = st.size();
				answer += um[st[i-1]];
				if (st.size() >= 2 && st.back() == vec[index]) {
					// answer += um[st[i-2]];
					answer++;
				}
			}
		}

		if (um.find(vec[index]) == um.end()) {
			um[vec[index]] = 1;
			st.push_back(vec[index]);
		} else um[vec[index]]++;
		index++;
	}

	cout << answer;
}