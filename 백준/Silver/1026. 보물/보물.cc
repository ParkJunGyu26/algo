#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> A, B;

bool cmp(int a, int b) {
	return a > b;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	A.resize(n);
	B.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];
	for (int i = 0; i < n; i++) cin >> B[i];

	int answer = 0;
	sort(A.begin(), A.end());
	sort(B.begin(), B.end(), cmp);
	for (int i = 0; i < n; i++) answer += (A[i] * B[i]);

	cout << answer;
}