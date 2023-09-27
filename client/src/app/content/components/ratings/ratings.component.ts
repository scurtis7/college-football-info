import { Component, OnInit } from '@angular/core';
import { MatSelectChange } from "@angular/material/select";

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.scss']
})
export class RatingsComponent implements OnInit {

  years: string[] = ['2023', '2022', '2021', '2020', '2019', '2018', '2017', '2016', '2015'];
  selectedYear: string = '2023';

  // teams: string[] = ['', 'Clemson', 'Florida State', 'Wake Forest'];
  // selectedTeam: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  selectYearEvent(event: MatSelectChange) {
    console.log("Year selected: " + this.selectedYear);
  }

  // selectChangeEvent(event: MatSelectChange) {
  //   console.log(event.source);
  //   console.log("Team selected: " + this.selectedTeam);
  // }

}
