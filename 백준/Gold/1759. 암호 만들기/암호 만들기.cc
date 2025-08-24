#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_set>

using namespace std;

int L, c;
vector<char> password;
vector<string> answer;
unordered_set<char> us;

void dfs(int index, string str) {
	if (str.size() == L) {
		int cnt = 0;
		for (int i = 0; i < str.size(); i++) if (us.find(str[i]) != us.end()) cnt++;

		if (cnt > 0 && str.size() - cnt > 1) answer.push_back(str);
		return;
	}

	for (int i = index; i < c; i++) {
		str += password[i];
		dfs(i+1, str);
		str.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> L >> c;
	password.resize(c);
	for (int i = 0; i < c; i++) cin >> password[i];
	sort(password.begin(), password.end());
	
	us.insert('a');
	us.insert('e');
	us.insert('i');
	us.insert('o');
	us.insert('u');

	dfs(0, "");
	for (auto ans : answer) cout << ans << "\n";
}