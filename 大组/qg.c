#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

typedef struct node {
    char data;             
    struct node* next;      
} node;
typedef struct {
    node* top;              
} Stack;
void init(Stack* n) {
    n->top = NULL;
}
int isempty(Stack* a) {
    return a->top == NULL;
}
void push(Stack* a, char x) {
    node* newnode = (node*)malloc(sizeof(node));  
    newnode->data = x;                           
    newnode->next = a->top;                     
    a->top = newnode;                            
}
char pop(Stack* a) {
    if (isempty(a)) {
        printf("no data\n");
        exit(1);
    }
    node* temp = a->top;    
    char value = temp->data; 
    a->top = temp->next;    
    free(temp);             
    return value;           
}
char gettop(Stack* a) {
    if (isempty(a)) {
        printf("栈no data\n");
        exit(1);
    }
    return a->top->data;
}
int pre(char x) {
    switch (x) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        default:
            return 0;
    }
}

void infix_to_postfix(char* a, char* b) {
    Stack x;
    init(&x);  
    int j = 0; 

    for (int i = 0; a[i]; i++) {
        if (a[i] == ' ') {
            continue;  // 
        }
        if (isdigit(a[i])) {
            while (a[i] && isdigit(a[i])) {
                b[j++] = a[i++];  
            }
            b[j++] = ' ';  
            i--;          
        } else if (a[i] == '(') {
            push(&x, a[i]);  
        } else if (a[i] == ')') {
            while (!isempty(&x) && gettop(&x) != '(') {
                b[j++] = pop(&x);  
                b[j++] = ' ';
            }
            pop(&x);  
        } else {
            while (!isempty(&x) && pre(gettop(&x)) >= pre(a[i])) {
                b[j++] = pop(&x);  
                b[j++] = ' ';
            }
            push(&x, a[i]); 
        }
    }

    while (!isempty(&x)) {
        b[j++] = pop(&x);
        b[j++] = ' ';
    }
    b[j] = '\0';  
}
int calculate(char* b) {
    Stack a;
    init(&a);  

    for (int i = 0; b[i]; i++) {
        if (isspace(b[i])) {
            continue;  
        }
        if (isdigit(b[i])) {
            int num = 0;
            while (b[i] && isdigit(b[i])) {
                num = num * 10 + (b[i] - '0');  
                i++;
            }
            push(&a, num);  
            i--;            
        } else {
            int val2 = pop(&a);  
            int val1 = pop(&a);  
            switch (b[i]) {
                case '+':
                    push(&a, val1 + val2);
                    break;
                case '-':
                    push(&a, val1 - val2);
                    break;
                case '*':
                    push(&a, val1 * val2);
                    break;
                case '/':
                    push(&a, val1 / val2);
                    break;
            }
        }
    }
    return pop(&a);  
}
int main() {
    char a[100];  
    char b[100];  
    printf("请输入表达式：");
    fgets(a, sizeof(a), stdin);  
    infix_to_postfix(a, b);    
    int result=calculate(b);
    printf("计算结果：%d\n", result);
    return 0;
}