#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int n, num, temp;
int visited[100001] = {0};

void bfs(int node, vector<vector<pair<int, int>>> &graph, vector<int> &res) {
	queue<int> q;
	q.push(node);
	visited[node] = 1;

	while (!q.empty()) {
		int now_node = q.front();
		q.pop();

		for (int i = 0; i < graph[now_node].size(); i++) {
			int next_node = graph[now_node][i].first;
			int next_res = graph[now_node][i].second;

			// cout << "n_node : " << next_node << "\n";
			// cout << "n_res : " << next_res << "\n";
			// cout << "----\n";

			if (visited[next_node] == 0) {
				visited[next_node] = 1;
				res[next_node] = res[now_node] + next_res;
				q.push(next_node);
			}
		}
	}
}

// bfs, dfs를 각각 한 번씩 하자
// 1번 : 루트에서 제일 멀리있는 곳
// 2번 : 루트에서 제일 먼 곳에서 제일 먼 곳
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<vector<pair<int, int>>> graph(n+1); 
	for (int i = 0; i < n; i++) {
		cin >> num;

		vector<int> tmp;
		while (cin >> temp) {
			if (temp == -1) break;
			tmp.push_back(temp);
		}

		for (int j = 0; j < tmp.size()/2; j++)
			graph[num].push_back({tmp[2*j], tmp[2*j+1]});
	}

	// for (int i = 1; i <= n; i++) {
	// 	for (int j = 0; j < graph[i].size(); j++) cout << "i : " << i << " : " << graph[i][j].first << " " << graph[i][j].second << "\n";
	// 	cout << "\n";
	// }

	vector<int> res(n+1);
	bfs(1, graph, res); // res 깂 세팅(1번)
	int MAX = *max_element(res.begin(), res.end());
	
	// for (int i = 1; i <= n; i++)
	// 	cout << "i : " << i << ", " << res[i] << "\n";

	// cout << "max : " << MAX << "\n";
	int hubo_node;
	for (int i = 1; i <= n; i++) {
		if (res[i] == MAX) {
			hubo_node = i;
		}
		visited[i] = 0;
	}

	vector<int> res2(n+1);
	bfs(hubo_node, graph, res2);
	MAX = *max_element(res2.begin(), res2.end());
	cout << MAX;
}