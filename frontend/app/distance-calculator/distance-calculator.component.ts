import { Component } from '@angular/core';
import { PostcodeService } from '../services/postcode.service';
import { HttpErrorResponse } from '@angular/common/http';

// Define an interface for the distance result
export interface DistanceResult {
  postcode1: string;
  postcode2: string;
  latitude1: number;
  longitude1: number;
  latitude2: number;
  longitude2: number;
  distance: number;
  unit: string;
}

@Component({
  selector: 'app-distance-calculator',
  templateUrl: './distance-calculator.component.html',
  styleUrls: ['./distance-calculator.component.css']
})
export class DistanceCalculatorComponent {
  postcode1: string = '';
  postcode2: string = '';
  result: DistanceResult | null = null;
  loading: boolean = false;
  error: string = '';

  constructor(private postcodeService: PostcodeService) {}

  calculateDistance(): void {
    if (!this.postcode1 || !this.postcode2) {
      this.error = 'Please enter both postcodes';
      return;
    }

    this.loading = true;
    this.error = '';
    this.result = null;

    this.postcodeService.getDistance(this.postcode1, this.postcode2)
      .subscribe({
        next: (data: DistanceResult) => {
          this.result = data;
          this.loading = false;
        },
        error: (err: HttpErrorResponse) => {
          this.error = err.error?.message || 'An error occurred while calculating distance';
          this.loading = false;
        }
      });
  }

  resetForm(): void {
    this.postcode1 = '';
    this.postcode2 = '';
    this.result = null;
    this.error = '';
  }
} 