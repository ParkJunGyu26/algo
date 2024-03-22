#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string overwrite_string, int s) {
    string answer = my_string.substr(0, s);
    int over_len = overwrite_string.size();
    int my_len = my_string.size();
    if (my_len > over_len + s) {
        answer += (overwrite_string + my_string.substr(s+over_len));
    } else {
        answer += overwrite_string;
    }
    return answer;
}