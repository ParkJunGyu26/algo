#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long n, b;

vector<vector<int>> dot(vector<vector<int>> &new_A, vector<vector<int>> &A) {
	vector<vector<int>> update(n, vector<int>(n));

	for (int i = 0; i < n; i++) { // 새로운 행
		for (int j = 0; j < n; j++) { // 새로운 열
			int tmp = 0;
			for (int k = 0; k < n; k++) {
				tmp += (new_A[i][k] * A[k][j]);
			}
			update[i][j] = tmp % 1000;
		}
	}

	return update;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> b;
	vector<vector<int>> A(n, vector<int>(n));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) cin >> A[i][j];
	
	vector<int> bin;
	while (true) {
		bin.push_back(b%2);
		if (b / 2 == 0) break;
		b /= 2;
	}

	reverse(bin.begin(), bin.end());

	vector<vector<int>> answer;
	if (bin.back() == 1) answer = A;
	vector<vector<int>> new_A = A;

	for (int i = bin.size()-1; i >= 0; i--) {
		if (bin[i] == 1 && i != bin.size()-1) {
			if (answer.size() == 0) answer = new_A;
			else answer = dot(answer, new_A);
		}
		new_A = dot(new_A, new_A); // (A^2, A^4, A^8, ...)
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) cout << answer[i][j]%1000 << " ";
		cout << "\n";
	}
}