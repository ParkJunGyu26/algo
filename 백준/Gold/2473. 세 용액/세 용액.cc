#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int n;
vector<long long> vec;
vector<long long> answer(3);

long long SUM(vector<long long> &vec) {
	long long result = 0;
	for (auto v : vec) result += v;
	return result;
}

void setting(int i, int j, int mid) {
	answer[0] = vec[i];
	answer[1] = vec[j];
	answer[2] = vec[mid];

	sort(answer.begin(), answer.end());
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];
	sort(vec.begin(), vec.end());

	answer[0] = vec[0];
	answer[1] = vec[1];
	answer[2] = vec[2];

	long long now = abs(SUM(answer));
	for (int i = 0; i < n-2; i++) {
		for (int j = i+1; j < n-1; j++) {
			int left = j+1;
			int right = n;

			int mid;
			while (left < right && right <= n) {
				mid = (left + right) / 2;

				long long target = vec[mid] + vec[i] + vec[j];

				if (target == 0) {
					setting(i, j, mid);

					for (auto a : answer) cout << a << " ";
					return 0;
				} else if (target < 0) {
					left = mid+1;
				} else {
					right = mid;
				}

				if (now > abs(target)) {
					setting(i, j, mid);
					now = abs(target);
				}
			}
		}
	}

	for (auto a : answer) cout << a << " ";
}