#include <iostream>
#include <string>
#include <vector>

using namespace std;

string a, b;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	while (getline(cin, a) && getline(cin, b)) {
		string answer = "";
		vector<int> A(26);
        vector<int> B(26);

		for (int i = 0; i < a.size(); i++) A[a[i] - 'a']++;
		for (int i = 0; i < b.size(); i++) B[b[i] - 'a']++;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < min(A[i], B[i]); j++) answer += (i + 'a');
		}
		cout << answer << "\n";
	}
}