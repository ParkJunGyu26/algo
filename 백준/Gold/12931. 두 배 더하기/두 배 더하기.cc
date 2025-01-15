#include <iostream>
#include <vector>

using namespace std;

int n;

bool all_zero(vector<int> &A) {
	for (auto a : A) {
		if (a != 0) return false;
	}
	return true;
}

bool two_check(vector<int> &A) {
	for (auto a : A) {
		if (a % 2 != 0) return false;
	}
	return true;
}

void divide(vector<int> &A) {
	for (int i = 0; i < A.size(); i++)
		A[i] /= 2;
}

void one_minus(vector<int> &A) {
	for (int i = 0; i < A.size(); i++) {
		if (A[i] % 2 != 0) {
			A[i]--;
			return;
		}
	}
}

// 탑다운 방식으로
int solution(vector<int> &A) {
	int answer = 0;
	
	while (!all_zero(A)) {
		answer++;

		if (two_check(A)) divide(A);
		else one_minus(A);
	}

	return answer;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<int> A(n);
	for (int i = 0; i < n; i++) cin >> A[i];
	
	cout << solution(A);
}