#include <iostream>
#include <string>

using namespace std;

int n, m;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
	cin >> n >> m;
	string answer = (100 * n >= m) ? "Yes" : "No";
	cout << answer;
}