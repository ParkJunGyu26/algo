#include <iostream>
#include <vector>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<long long> distance(n-1);
	vector<long long> city(n);
	for (int i = 0; i < n-1; i++) cin >> distance[i];
	for (int i = 0; i < n; i++) cin >> city[i];
	
	long long min_money = city[0];
	long long prefix_distance = 0;
	long long answer = 0;
	for (int i = 1; i < n; i++) {
		prefix_distance += distance[i-1];

		if (city[i] <= min_money) {
			answer += (prefix_distance * min_money);
			min_money = city[i];
			prefix_distance = 0;
		}
	}

	if (prefix_distance != 0) answer += (prefix_distance * min_money);

	cout << answer;
}