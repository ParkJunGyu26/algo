#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int answer = INT_MAX;
int n;
vector<int> A;

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);
	cin >> n;
	A.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];

	int left = 0;
	int right = n-1;

	while (left < right) {
		int sum = A[left] + A[right];

		if (sum == 0) {
			cout << 0;
			return 0;
		}

		if (abs(answer) > abs(sum)) answer = sum;

		if (sum < 0) left++;
		else right--;
	}

	cout << answer;
}