#include <iostream>
#include <vector>

using namespace std;

int n, k;
int answer = 0;
vector<int> vec;

void check(vector<int> &hubo) {
	int weight = 500;

	for (int i = 0; i < n; i++) {
		weight -= k;
		weight += hubo[i];

		if (weight < 500) return;
	}

	answer++;
}

void dfs(vector<int> &hubo, vector<bool> &visited) {
	if (hubo.size() == n) {
		check(hubo);
		return;
	}

	for (int i = 0; i < n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			hubo.push_back(vec[i]);

			dfs(hubo, visited);

			visited[i] = false;
			hubo.pop_back();
		}
	}
}

// 순열만들고, 그 값으로 탐색
int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n >> k;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	for (int i = 0; i < n; i++) {
		vector<int> hubo;
		vector<bool> visited(n);
		visited[i] = true;
		hubo.push_back(vec[i]);

		dfs(hubo, visited);
	}

	cout << answer;
}