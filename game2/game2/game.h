#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>

#define EASY_GOAL 10   //设置雷的数量
#define ROW 9   //设置游戏的规模
#define COL 9	//设置游戏的规模

#define ROWS (ROW+2)
#define COLS (COL+2)



void menu();
void game();
void Initboard(char arr[ROWS][COLS], int row, int col, char set);  //初始化二维数组
void set_mine(char arr[ROWS][COLS], int row, int col);  //随机设置雷的位置
void Printboard(char arr[ROWS][COLS], int row, int col);  //打印游戏界面
void Judge(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col); //判断游戏结果
int Count(char arr[ROWS][COLS], int x, int y);			//判断所选点位周围累的数量
void Unfold(char mine[ROWS][COLS], char show[ROWS][COLS], int x, int y);  //展开周围没有雷的坐标
