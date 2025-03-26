#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <queue>

using namespace std;

vector<int> vec;
bool visited[1000][1000];

int sum() {
    int a = 0;
    for (auto v : vec) a += v;
    return a;
}

struct Point{
int a;
int b;
int c;
};

void bfs() {
	queue<Point> q;
	q.push({vec[0], vec[1], vec[2]});
	visited[vec[0]][vec[1]] = true;

	while(!q.empty()) {
		int a = q.front().a;
		int b = q.front().b;
		int c = q.front().c;
		q.pop();

		if (a == b && b == c && a == c) {
			cout << 1;
			return;
		}

		vec[0] = a+a;
		vec[1] = b-a;
		vec[2] = c;

		if (!visited[min(vec[0], vec[1])][max(vec[0], vec[1])]) {
			visited[min(vec[0], vec[1])][max(vec[0], vec[1])] = true;
			sort(vec.begin(), vec.end());
			q.push({vec[0], vec[1], vec[2]});
		}

		vec[0] = a+a;
		vec[1] = b;
		vec[2] = c-a;

		if (!visited[min(vec[0], vec[2])][max(vec[0], vec[2])]) {
			visited[min(vec[0], vec[2])][max(vec[0], vec[2])] = true;
			sort(vec.begin(), vec.end());
			q.push({vec[0], vec[1], vec[2]});
		}

		vec[0] = a;
		vec[1] = b+b;
		vec[2] = c-b;

		if (!visited[min(vec[2], vec[1])][max(vec[2], vec[1])]) {
			visited[min(vec[2], vec[1])][max(vec[2], vec[1])] = true;
			sort(vec.begin(), vec.end());
			q.push({vec[0], vec[1], vec[2]});
		}
	}

	cout << 0;
	return;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vec.resize(3);
    for (int i = 0; i < 3; i++) cin >> vec[i];
    sort(vec.begin(), vec.end());

	int total = sum();
	if (total % 3 != 0) {
		cout << 0;
		return 0;
	}

	bfs();
} 