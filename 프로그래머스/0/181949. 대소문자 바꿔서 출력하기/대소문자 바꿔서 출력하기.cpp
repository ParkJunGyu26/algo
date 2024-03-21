#include <iostream>
#include <string>

using namespace std;

int main(void) {
    string str;
    cin >> str;
    
    for (char& c : str) {
        if (isupper(c)) {
            c = tolower(c);
        } else {
            c = toupper(c);
        }
    }
    
    cout << str << endl;
    return 0;
}