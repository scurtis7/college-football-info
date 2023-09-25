import { Component, OnInit } from '@angular/core';
import { MatSelectChange } from "@angular/material/select";

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.scss']
})
export class RatingsComponent implements OnInit {

  teams: string[] = ['Clemson', 'Florida State', 'Wake Forest'];
  selectedTeam: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  selectChangeEvent(event: MatSelectChange) {
    console.log(event.source);
    console.log("Team selected: " + this.selectedTeam);
  }

}
