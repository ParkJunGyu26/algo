#include <iostream>
#include <vector>
#include <string>
#include <set>

using namespace std;

int visited[8] = {0};
string n;
set<vector<string>> answer;

void dfs(vector<string> &hubo, string &hubo_string) {
	if (hubo_string.size() == n.size()) {
		if (hubo_string == n) {
			answer.insert(hubo);
			// for (auto h : hubo) cout << h << " ";
			// cout << "\n";
		}
		return;
	}

	for (int i = 0; i < n.size(); i++) {
		if (visited[i] == 0) {
			visited[i] = 1;
			hubo_string += n[i];
			hubo.push_back(hubo_string);

			dfs(hubo, hubo_string);

			hubo_string.pop_back();
			hubo.pop_back();

			string tmp = "";
			tmp += n[i];
			tmp += hubo_string;

			hubo.push_back(tmp);
			dfs(hubo, tmp);

			hubo.pop_back();
			visited[i] = 0;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n.size(); i++) {
		visited[i] = 1;
		string hubo_string = "";
		hubo_string += n[i];

		vector<string> hubo;
		hubo.push_back(hubo_string);

		dfs(hubo, hubo_string);
		visited[i] = 0;
	}

	cout << answer.size();
}