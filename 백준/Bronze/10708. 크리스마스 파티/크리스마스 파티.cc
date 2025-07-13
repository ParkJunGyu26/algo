#include <iostream>
#include <vector>

using namespace std;

int n, m;
vector<int> A, score;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m;
	score.resize(n);
	A.resize(m);
	for (int i = 0; i < m; i++) cin >> A[i];
	for (int i = 0; i < m; i++) {
		vector<int> B(n);
		
		int wrong = 0;
		for (int j = 0; j < n; j++) {
			cin >> B[j]; 
			if (A[i] == B[j]) score[j]++;
			else wrong++;
		}

		score[A[i]-1] += wrong;
	}

	for (int i = 0; i < n; i++) cout << score[i] << "\n";
}