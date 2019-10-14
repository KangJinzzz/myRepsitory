
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>

#define ROW 3	//行		设定游戏规模
#define COL 3	//列

void menu();
void game();
void Initboard(char board[ROW][COL], int row, int col);  //初始化棋盘	
void Printboard(char board[ROW][COL], int row, int col);		//打印棋盘
void palyer_move(char board[ROW][COL], int row, int col);	//玩家下棋
void computer_move(char board[ROW][COL], int row, int col);	//电脑下棋
char Judge(char board[ROW][COL], int row, int col);   //判断输赢条件，玩家赢返回‘*’，电脑赢返回‘#’，
												  //平局返回‘p’,游戏未分出胜负，返回‘c’