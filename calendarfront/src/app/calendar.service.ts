import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Calendar} from "./calendar";
import {Availability} from "./availability";
import {CreateRequestAvailability} from "./create.request.availability";
import {DeleteRequestAvailability} from "./delete.request.availability";
import {CreateRequestReservation} from "./create.request.reservation";
import {Reservation} from "./reservation";
import {DeleteRequestReservation} from "./delete.request.reservation";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCalendarDetails(): Observable<Calendar>{
    return this.http.get<Calendar>(`${this.apiServerUrl}/calendar/all`);
  }

  public createAvailability(request: CreateRequestAvailability): Observable<Availability>{
    return this.http.post<Availability>(`${this.apiServerUrl}/calendar/create/availability`, request);
  }

  public deleteAvailability(request: DeleteRequestAvailability): Observable<void>{
    return this.http.post<void>(`${this.apiServerUrl}/calendar/delete/availability`, request);
  }

  public createReservation(request: CreateRequestReservation): Observable<Reservation>{
    return this.http.post<Reservation>(`${this.apiServerUrl}/calendar/create/reservation`, request);
  }

  public deleteReservation(request: DeleteRequestReservation): Observable<void>{
    return this.http.post<void>(`${this.apiServerUrl}/calendar/delete/reservation`, request);
  }
}
