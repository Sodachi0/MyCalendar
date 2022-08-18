import {Component, OnInit} from '@angular/core';
import {Calendar} from "./calendar";
import {CalendarService} from "./calendar.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public calendar: Calendar = {availabilities: [], reservations: []};

  constructor(private calendarService: CalendarService) {}

  ngOnInit() {
    this.getCalendar();
  }

  public getCalendar(): void {
    this.calendarService.getCalendarDetails().subscribe(
      (response: Calendar) => {
        this.calendar = response;
      }, error => {
        alert(error.message);
      }
    );
}
}
