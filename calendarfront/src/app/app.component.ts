import {Component, OnInit} from '@angular/core';
import {Calendar} from "./calendar";
import {CalendarService} from "./calendar.service";
import {Reservation} from "./reservation";
import {NgForm} from "@angular/forms";
import {CreateRequestAvailability} from "./create.request.availability";
import {DatePipe} from "@angular/common";
import {Availability} from "./availability";
import {HttpErrorResponse} from "@angular/common/http";
import {CreateRequestReservation} from "./create.request.reservation";
import {DeleteRequestAvailability} from "./delete.request.availability";
import {DeleteRequestReservation} from "./delete.request.reservation";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public calendar: Calendar = {availabilities: [], reservations: []};
  public availability: Availability = {id: 0, start: "", end: ""};
  public reservation: Reservation = {id: 0, availabilityId: 0, start: "", end: "", title: ""};

  constructor(private calendarService: CalendarService, private datePipe: DatePipe) {}

  ngOnInit() {
    this.getCalendar();
  }

  public getCalendar(): void {
    this.calendarService.getCalendarDetails().subscribe(
      (response: Calendar) => {
        this.calendar = response;
        this.calendar.availabilities.sort((e1, e2) => this.dateSort(e1.start,e2.start));
        this.calendar.reservations.sort((e1, e2) => this.dateSort(e1.start,e2.start));
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getReservationsByAvailability(availabilityId: number): Reservation[] {
    return this.calendar.reservations.filter((reservation) => {
      return reservation.availabilityId == availabilityId;
    });
  }

  public onCreateAvailability(form: NgForm): void {
    let request = form.value as CreateRequestAvailability;
    request.start = this.formatDate(request.start);
    request.end = this.formatDate(request.end);

    this.calendarService.createAvailability(request).subscribe(
      (response: Availability) => {
        console.log(response);
        this.getCalendar();
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    form.reset();
    this.closeForm("create-availability-close");
  }

  public onCreateReservation(form: NgForm): void {
    let request = form.value as CreateRequestReservation;
    request.start = this.formatDate(request.start);
    request.end = this.formatDate(request.end);

    this.calendarService.createReservation(request).subscribe(
      (response: Reservation) => {
        console.log(response);
        this.getCalendar();
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    form.reset();
    this.closeForm("create-reservation-close");
  }

  public onDeleteAvailability(form: NgForm): void {
    let request = form.value as DeleteRequestAvailability;

    this.calendarService.deleteAvailability(request).subscribe(
      (response: void) => {
        console.log(response);
        this.getCalendar();
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    form.reset();
    this.closeForm("delete-availability-close")
  }

  public onDeleteReservation(form: NgForm): void {
    let request = form.value as DeleteRequestReservation;

    this.calendarService.deleteReservation(request).subscribe(
      (response: void) => {
        console.log(response);
        this.getCalendar();
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    form.reset();
    this.closeForm("delete-reservation-close")
  }

  public onOpenModal(modalId: string, availability?: Availability, reservation?: Reservation): void {
    const container = document.getElementById('main-container');
    if (container) {
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      button.setAttribute('data-target', '#' + modalId);
      if(availability)
        this.availability = availability;
      if (reservation)
        this.reservation = reservation;
      container.appendChild(button);
      button.click();
    }
  }

  private closeForm(closeButtonId: string): void {
    let modal = document.getElementById(closeButtonId)
    if (modal)
      modal.click();
  }

  private formatDate(date: string): string {
    let formattedDate = this.datePipe.transform(new Date(date), "dd/MM/yyyy HH:mm");
    if (formattedDate)
      return formattedDate.toString();
    return "";
  }

  private formatStringToDate(date: string): Date {
    let part1 = date.split(" ");
    let part2 = part1[1].split(":");
    part1 = part1[0].split("/");
    return new Date(+part1[2], +part1[1], +part1[0], +part2[0], +part2[1]);
  }

  private dateSort(e1: string, e2: string): number {
    return this.formatStringToDate(e1).getTime() - this.formatStringToDate(e2).getTime();
  }
}
