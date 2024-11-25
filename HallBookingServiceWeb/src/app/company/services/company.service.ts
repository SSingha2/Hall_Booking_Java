import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  postHall(hallDTO: any): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.post(BASIC_URL + `api/company/hall/${userId}`, hallDTO, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAllHallsByUserId(): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/company/halls/${userId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getHallById(hallId:any): Observable<any>{
    return this.http.get(BASIC_URL + `api/company/hall/${hallId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getAllHallBookins(): Observable<any>{
    const companyId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/company/bookings/${companyId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  changeBookingStatus(bookingId: number, status: string): Observable<any>{
    return this.http.get(BASIC_URL + `api/company/booking/${bookingId}/${status}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  updateHall(hallId: any, hallDTO: any):Observable<any>{
    return this.http.put(BASIC_URL + `api/company/hall/${hallId}`, hallDTO, {
      headers : this.createAuthorizationHeader()
    })
  }

  deleteHall(hallId: any):Observable<any>{
    return this.http.delete(BASIC_URL + `api/company/hall/${hallId}`,{
      headers : this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader() : HttpHeaders {
    let authHeaders : HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer '+ UserStorageService.getToken()
    )
  }
}
