#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m, num;
vector<int> parent(51);
vector<int> know(51);
vector<vector<int>> party(51);

int find(int node) {
	if (parent[node] == node) return node;
	return parent[node] = find(parent[node]);
}

void merge(int a, int b) {
	a = find(a);
	b = find(b);

	if (a == b) return;

	if (know[b] == 1) swap(a, b);

	parent[b] = a;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	for (int i = 1; i <= n; i++) parent[i] = i; // 루트 노드 초기화

	cin >> num;
	for (int i = 0; i < num; i++) {
		int who;
		cin >> who;
		know[who] = 1;
	}

	for (int i = 1; i <= m; i++) {
		cin >> num;
		for (int j = 0; j < num; j++) {
			int person;
			cin >> person;
			party[i].push_back(person);
		}
	}

	for (int i = 1; i <= m; i++) {
		if (party[i].size() == 1) continue;

		sort(party[i].begin(), party[i].end());
		int target = party[i][0];
		for (int j = 1; j < party[i].size(); j++) {
			merge(target, party[i][j]);
		}
	}

	int answer = 0;
	for (int i = 1; i <= m; i++) {
		bool check = true;
		for (int j = 0; j < party[i].size(); j++) {
			if (know[find(party[i][j])] == 1) {
				check = false;
				break;
			}
		}
		if (check) answer++;
	}

	cout << answer;
}