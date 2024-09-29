#include <iostream>
#include <string>

using namespace std;

int cnt;

void recur(string& s, int left, int right) {
	if (left >= right) {
		cnt++;
		return;
	}

	cnt++;
	if (s[left] == s[right]) {
		recur(s, left+1, right-1);
	}
	else return;
}

int isPalin(string& s) {
	recur(s, 0, s.size()-1);

	if (cnt == s.size() / 2 + 1) return 1;
	return 0;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int t;
	cin >> t;

	string A[1000];
	for (int i = 0; i < t; i++)
		cin >> A[i];
	
	for (int i = 0; i < t; i++) {
		cnt = 0;
		int is = isPalin(A[i]);
		cout << is << " " << cnt << endl;
	}
}