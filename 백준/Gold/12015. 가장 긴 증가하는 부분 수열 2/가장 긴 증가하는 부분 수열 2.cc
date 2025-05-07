#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> A, answer;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	A.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];

	answer.push_back(A[0]);

	for (int i = 1; i < n; i++) {
		if (answer.back() == A[i]) continue;
		else if (answer.back() < A[i]) answer.push_back(A[i]);
		else {
			int left = 0; // 범위 안
			int right = answer.size(); // 범위 밖

			int mid;
			while (left < right) {
				mid = (left + right) / 2;

				if (answer[mid] < A[i]) left = mid+1;
				else right = mid;
			}

			answer[left] = A[i];
		}
	}

	cout << answer.size();
}