#include <stdio.h>
int insert(int *a){
    for(int i=1;i<10;i++){
        int j=i-1;
        int k=a[i];
        while(k<a[j]&& j>=0){
            a[j+1]=a[j];
            j--;
        }
        a[j+1]=k;
    }
}
void merge(int arr[], int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int *L = (int *)malloc(n1 * sizeof(int));
    int *R = (int *)malloc(n2 * sizeof(int));

    for (int i = 0; i < n1; i++) {
        L[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[mid + 1 + j];
    }

    int i = 0, j = 0, k = left;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }
    while (i < n1) {
        arr[k++] = L[i++];
    }
    while (j < n2) {
        arr[k++] = R[j++];
    }
    free(L);
    free(R);
}

int * merge(int *a,int *b,int a1,int b1){
    int n=a1+b1;
    int *c=(int *)malloc(sizeof(int)*n);
    int k=0;
    int i,j;
    for( i=0,j=0;i<a1&&j<b1;){
        if(a[i]>b[j]){
            c[k]=b[j];
            j++;
            k++;
        }
        else{
            c[k]=a[i];
            i++;
            k++;
        }
    }
    if(i<a1){
        while(i<a1){
            c[k]=b[j];
            k++;
            j++;
        }
    }
    else{
        while(j<b1){
            c[k]=a[i];
            k++;
            i++;
        }
    }
    return c;
}
void swap(int *a,int *b){
    int temp=*a;
     *a=*b;
     *b=temp;
}
int partition(int arr[], int low, int high) {
    int pivot = arr[high];  
    int i = (low - 1);      

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] <= pivot) {
            i++;  
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);  
    return (i + 1);
}
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
int main(){
    printf("请你输入十个数\n");
    int a[10]={0};
    for(int i=0;i<10;i++){
        scanf("%d",&a[i]);
        printf("输入成功\n");
    }
    printf("请你选择排序的方法\n");
    printf("1. 插入\n");
    printf("2. 归并\n");
    printf("3. 快速排序法\n");
    int choice;
    scanf("%d",&choice) ;
    switch(choice){       
        case 1:  
        insert(a);
        break;
        case 2:
        merge(a,0,4,9);
        break;
        case 3:
        quicksort(a,0,9);
        break;
        default:
        printf("无效选项");
        break;
    }
    for(int i=0;i<10;i++){
        printf("%d ",a[i]);
    }
    return 0;
}