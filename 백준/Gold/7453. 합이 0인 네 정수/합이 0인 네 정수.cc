#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long answer = 0;
int n;
vector<vector<int>> vec;
vector<long long> hubo;

int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n;
	vec.resize(n, vector<int>(4));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < 4; j++) cin >> vec[i][j];

	for (int i = 0; i < n; i++) {
		int a = vec[i][0];
		
		for (int j = 0; j < n; j++) {
			int b = vec[j][1];

			hubo.push_back(a+b);
		}
	}

	sort(hubo.begin(), hubo.end());

	for (int i = 0; i < n; i++) {
		int c = vec[i][2];

		for (int j = 0; j < n; j++) {
			int d = vec[j][3];

			long long target = c+d;

			bool check = binary_search(hubo.begin(), hubo.end(), -target);

			if (check) {
				answer += abs(lower_bound(hubo.begin(), hubo.end(), -target) - upper_bound(hubo.begin(), hubo.end(), -target));
			}
		}
	}

	cout << answer;
}