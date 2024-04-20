#include <iostream>
#include <string>

using namespace std;
#define MAX_STACK_SIZE 10000

class Stack
{
private:
    int pointer;
    int data[MAX_STACK_SIZE];

public:
    Stack() {
        pointer = -1;
    };

    ~Stack() {};

    void push(int e) {
        data[++pointer] = e;
    };

    int pop() {
        if (empty()) return -1;
    
        return data[pointer--];
    };

    int size() {
        return (pointer+1);
    };
    
    int empty() {
        if (pointer == -1) {
            return 1;
        }
        return 0;
    };

    int top() {
        if (empty()) return -1;

        return data[pointer];
    };
};

int main() {
    Stack stack;
    int n;
    cin >> n;
    
    for (int i = 0; i < n; i++) {
        string M;
        int num;

        cin >> M;
        if (M == "push") {
            cin >> num;
            stack.push(num);
        }

        if (M == "top")
            cout << stack.top() << endl;
        
        if (M == "size")
            cout << stack.size() << endl;

        if (M == "empty")
            cout << stack.empty() << endl;
        
        if (M == "pop")
            cout << stack.pop() << endl;
    }
}