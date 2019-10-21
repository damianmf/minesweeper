import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

class Cell {
  constructor(x,y, isRevealed, isMine=false, peers=0){
    this.x=x;
    this.y=y;
    this.isRevealed = isRevealed;
    this.isMine = isMine;
    this.peers = peers;
  }
  x:number;
  y:number;
  isMine:boolean;
  isRevealed:boolean;
  peers:number;
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'minesweeper';
  board:Array<Array<Cell>>;
  boardId:number;
  gameId:number;
  private baseUrl: string = "http://localhost:8080/api/v1/";

  constructor(private httpClient: HttpClient){
    this.initializeBoard();
  }

  private initializeBoard() {
    this.httpClient.post(this.baseUrl + "/games",{})
      .subscribe((res:any)=>{
        console.log(res);
        this.gameId = res.id;
        this.boardId = res.board.id;
      });
    this.board = this.initBoard(5,5);
  }

  public reveal(cell: Cell){
    this.httpClient.patch(this.baseUrl + "games/"+this.gameId+"/board/"+this.boardId+"/reveal",
      {row:cell.y, col:cell.x})
      .subscribe((res:any)=>{
        console.log(res);
        if(res.mine){
          alert("You lose");
        }else{
          this.board[res.col][res.row] = new Cell(res.col, res.row, true, res.mine, res.peers);
        }

      });
  }

  private initBoard(row: number, col: number) {
    let array = new Array(row);
    for(let i = 0;i<array.length;i++){
      array[i] = new Array(col);
      for(let y = 0;y<array[i].length;y++){
        array[i][y] = new Cell(i, y, false);
      }
    }
    return array;
  }
}
