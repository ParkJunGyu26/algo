#include <iostream>
#include <vector>
#include <queue>
#include <deque>

using namespace std;

struct Snake{
	deque<pair<int, int>> track;
};

int n, k, l;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> graph;
vector<vector<int>> visited;
vector<char> snakeInfo;

void setApple() {
	for (int i = 0; i < k; i++) {
		int r, c;
		cin >> r >> c;
		graph[r-1][c-1] = 1;
	}
}

void setInfo() {
	for (int i = 0; i < l; i++) {
		int time;
		char dire;

		cin >> time >> dire;
		snakeInfo[time] = dire;
	}
}

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < n);
}

void simulation() {
	queue<Snake> q;
	deque<pair<int, int>> start;
	int dire = 1;
	int result = 0;

	start.push_front({0, 0});
	q.push({start});
	visited[0][0] = 1;

	while(!q.empty()) {
		deque<pair<int, int>> track = q.front().track;
		q.pop();
		int x = track.front().first;
		int y = track.front().second;

		int nx = x + dx[dire%4];
		int ny = y + dy[dire%4];

		if (inRange(nx, ny) && visited[ny][nx] == 0) {
			result++;
			if (snakeInfo[result] == 'L') {
				dire--;
				if (dire == -1) dire = 3;
			} else if (snakeInfo[result] == 'D') {
				dire++;
				if (dire == 4) dire = 0;
			}

			if (graph[ny][nx] == 1) {
                graph[ny][nx] = 0;
				track.push_front({nx ,ny});
			} else {
				track.push_front({nx, ny});
				visited[track.back().second][track.back().first] = 0;
				track.pop_back();
			}
			visited[ny][nx] = 1;
			q.push({track});
		} else {
			cout << result+1;
			return;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
	cin >> n >> k;
	graph.resize(n, vector<int>(n));
	visited.resize(n, vector<int>(n));
	setApple();
	cin >> l;
	snakeInfo.resize(10001, '.');
	setInfo();
	simulation();
}