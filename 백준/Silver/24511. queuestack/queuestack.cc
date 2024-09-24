#include <iostream>
#include <vector>

#define MAX_SIZE 100000

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<int> ans;
	int A[MAX_SIZE], B[MAX_SIZE], C[MAX_SIZE];
	int n, m;
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> A[i];

	for (int i = 0; i < n; i++)
		cin >> B[i];
	
	cin >> m;
	for (int i = 0; i < m; i++)
		cin >> C[i];
	
	for (int i = n-1; i >= 0; i--) {
		if (m == 0) break;
		if (A[i] == 0) {
			ans.push_back(B[i]);
			m--;
		}
	}

	for (int i = 0; i < m; i++)
		ans.push_back(C[i]);

	for (auto a : ans)
		cout << a << " ";
}