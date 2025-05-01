#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int n, m;
vector<int> vec;
vector<bool> visited;
set<vector<int>> answer;

void bt(vector<int> &hubo, int index) {
	if (hubo.size() == m) {
		answer.insert(hubo);
		return;
	}

	for (int i = index; i < n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			hubo.push_back(vec[i]);

			bt(hubo, i+1);

			hubo.pop_back();
			visited[i] = false;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vec.resize(n);
	visited.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];
	sort(vec.begin(), vec.end());

	vector<int> hubo;
	bt(hubo, 0);
	for (auto a : answer) {
		for (int i = 0; i < a.size(); i++) cout << a[i] << " ";
		cout << "\n";
	}
}