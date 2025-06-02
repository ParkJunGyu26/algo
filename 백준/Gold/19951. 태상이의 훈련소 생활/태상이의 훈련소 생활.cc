#include <iostream>
#include <vector>

using namespace std;

int n, m;
vector<int> H, PLUS, MINUS, mCheck, pCheck;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
	cin >> n >> m;
	H.resize(n);
	PLUS.resize(n);
	MINUS.resize(n);
	pCheck.resize(n);
	mCheck.resize(n);

	for (int i = 0; i < n; i++) cin >> H[i];
	for (int i = 1; i <= m; i++) {
		int s, e, d;
		cin >> s >> e >> d;

		PLUS[s-1] += d;
		MINUS[e-1] -= d;
		pCheck[s-1] = 1;
		mCheck[e-1] = -1;
	}

	int prefixSum = 0;
	for (int i = 0; i < n; i++) {
		if (pCheck[i] == 1) prefixSum += PLUS[i];

		H[i] += prefixSum;

		if (mCheck[i] == -1) prefixSum += MINUS[i];
	}

	for (int i = 0; i < n; i++) {
		cout << H[i];
		if (i != n-1) cout << " ";
	}
}