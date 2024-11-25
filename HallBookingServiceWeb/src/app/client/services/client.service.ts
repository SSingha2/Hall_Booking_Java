import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http:HttpClient) { }

  getAllHalls(): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/halls`, {
      headers : this.createAuthorizationHeader()
    })
  }

  searchHallByName(name: any): Observable<any>{
    return this.http.get(BASIC_URL + `api/client/search/${name}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  getHallDetailsById(hallId: any): Observable<any>{
    console.log(hallId);
    return this.http.get(BASIC_URL + `api/client/hall/${hallId}`, {
      headers : this.createAuthorizationHeader()
    })
  }

  bookHall(bookDTO: any): Observable<any>{
    return this.http.post(BASIC_URL + `api/client/book-hall`, bookDTO ,{
      headers : this.createAuthorizationHeader()
    })
  }

  giveReview(reviewDTO: any): Observable<any>{
    return this.http.post(BASIC_URL + `api/client/review`, reviewDTO ,{
      headers : this.createAuthorizationHeader()
    })
  }

  getMyBookings(): Observable<any>{
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/client/my-bookings/${userId}`,{
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
