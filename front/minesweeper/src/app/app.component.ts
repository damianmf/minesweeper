import { Component } from '@angular/core';

class Cell {
  constructor(x,y, isRevealed){
    this.x=x;
    this.y=y;
    this.isRevealed = isRevealed;
    this.peers = 0;
  }
  x:number;
  y:number;
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
  constructor(){
    this.initializeBoard();
  }

  private initializeBoard() {
    this.board = this.initBoard(10,10);
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
