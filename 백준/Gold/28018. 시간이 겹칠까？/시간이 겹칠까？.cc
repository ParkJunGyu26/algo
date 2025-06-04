#include <iostream>
#include <vector>

using namespace std;

int n, q;
int chair[1000002];
long long result[10000000];
vector<long long> answer;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		int s, e;
		cin >> s >> e;
		chair[s]++;
		chair[e+1]--;
	}

	long long cnt = 0;
	for (int i = 1; i <= 1000000; i++) {
		cnt += chair[i] * 1L;
		result[i] += cnt;
	}

	cin >> q;
	for (int i = 0; i < q; i++) {
		int time;
		cin >> time;
		answer.push_back(result[time]);
	}

	for (auto ans : answer) cout << ans << "\n";
}