#include <iostream>
#include <string>

using namespace std;

int min(int a, int b) {
	if (a > b) return b;
	return a;
}

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string str1, str2;
	int arr1[26] = {0};
	int arr2[26] = {0};

	cin >> str1 >> str2;

	for (int i = 0; i < str1.size(); i++) {
		arr1[str1[i]-'a']++;
	}
	for (int i = 0; i < str2.size(); i++) {
		arr2[str2[i]-'a']++;
	}

	int cnt = 0;
	for (int i = 0; i < 26; i++)
		cnt += max(arr1[i], arr2[i]) - min(arr1[i], arr2[i]);
	
	cout << cnt;
}