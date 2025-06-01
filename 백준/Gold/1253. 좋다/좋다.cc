#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<long long> A;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
	cin >> n;
	A.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];
	sort(A.begin(), A.end());

	int answer = 0;
	for (int k = 0; k < n; k++) {
		int i = 0;
		int j = n-1;

		while (i < j) {
			if (i == k) {
				i++;
				continue;
			}

			if (j == k) {
				j--;
				continue;
			}

			long long total = A[i] + A[j];
			if (total == A[k]) {
				answer++;
				break;
			}

			if (total < A[k]) i++;
			else j--;
		}
	}

	cout << answer;
}