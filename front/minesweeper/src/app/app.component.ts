import {Component} from '@angular/core';
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
  games: any;
  rows: any;
  cols: any ;
  mines: any;
  config: {rows:number, cols:number, mines:number} = {
    rows: 5,
    cols: 5,
    mines: 5
  };

  constructor(private httpClient: HttpClient){
    this.initGamesTable();
  }

  private initializeBoard() {
    this.httpClient.post(this.baseUrl + "games",{rows:this.config.rows,cols:this.config.cols,mines:this.config.mines})
      .subscribe((res:any)=>{
        console.log(res);
        this.gameId = res.id;
        this.boardId = res.boardId;
        this.games.push({id:res.id, status:res.status})
          this.board = this.initBoard(this.config.rows,this.config.cols);
        },
        err => alert(JSON.stringify(err)),);
  }

  public reveal(cell: Cell){
    this.httpClient.patch(this.baseUrl + "games/"+this.gameId+"/board/"+this.boardId+"/reveal",
      {row:cell.y, col:cell.x})
      .subscribe((res:any)=>{
        console.log(res);
        for(let i=0;i<res.cells.length;i++){
          if(res.cells[i].mine){
            alert("You lose");
            return;
          }else{
            this.board[res.cells[i].col][res.cells[i].row] = new Cell(res.cells[i].col, res.cells[i].row, true, res.cells[i].mine, res.cells[i].peers);
          }
        }
        if(res.toWin == 0){
          alert("You win");
          return;
        }
      });
  }

  private initBoard(row: number, col: number) {
    let array = new Array(Number(row));
    for(let i = 0;i<array.length;i++){
      array[i] = new Array(Number(col));
      for(let y = 0;y<array[i].length;y++){
        array[i][y] = new Cell(i, y, false);
      }
    }
    return array;
  }

  load(id: any) {
    this.httpClient.get(this.baseUrl + "games/" + id)
      .subscribe((res:any)=>{
        let game = res;
        this.httpClient.patch(this.baseUrl + "games/"+id+"/status",{status:"INPROGRESS"})
          .subscribe((res:any)=>{
            this.loadBoard(game);
          });
      });
    for (let i = 0; i<this.games.length;i++){
      if(this.games[i].id == id)
        this.games[i].status = "INPROGRESS";
    }
  }

  private loadBoard(game: any) {
    this.gameId = game.id;
    this.boardId = game.boardId;
    this.board = this.initBoard(game.row, game.col);
    for(let i=0;i<game.cells.length;i++){
      let cell = game.cells[i];
      this.board[cell.col][cell.row] =
        new Cell(cell.col, cell.row, true, cell.mine, cell.peers);
    }
  }

  private initGamesTable() {
    this.httpClient.get(this.baseUrl + "games")
      .subscribe((res:any)=>{
        this.games = res;
      });
  }

  newGame() {
    this.initializeBoard();
  }

  pause(id: any) {
    this.httpClient.patch(this.baseUrl + "games/"+id+"/status",{status:"PAUSED"})
      .subscribe((res:any)=>{
        for (let i = 0; i<this.games.length;i++){
          if(this.games[i].id == id)
            this.games[i].status = "PAUSED";
        }
      });
  }
}
