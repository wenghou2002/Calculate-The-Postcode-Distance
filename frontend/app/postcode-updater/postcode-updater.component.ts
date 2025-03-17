import { Component } from '@angular/core';
import { PostcodeService } from '../services/postcode.service';
import { HttpErrorResponse } from '@angular/common/http';

// Define an interface for the postcode update result
export interface PostcodeUpdateResult {
  postcode: string;
  latitude: number;
  longitude: number;
}

@Component({
  selector: 'app-postcode-updater',
  templateUrl: './postcode-updater.component.html',
  styleUrls: ['./postcode-updater.component.css']
})
export class PostcodeUpdaterComponent {
  postcode: string = '';
  latitude: string = '';
  longitude: string = '';
  result: PostcodeUpdateResult | null = null;
  loading: boolean = false;
  error: string = '';
  success: string = '';

  constructor(private postcodeService: PostcodeService) {}

  updatePostcode(): void {
    if (!this.postcode) {
      this.error = 'Please enter a postcode';
      return;
    }

    if (!this.latitude && !this.longitude) {
      this.error = 'Please enter at least one coordinate to update';
      return;
    }

    this.loading = true;
    this.error = '';
    this.success = '';
    this.result = null;

    const lat = this.latitude ? parseFloat(this.latitude) : null;
    const lng = this.longitude ? parseFloat(this.longitude) : null;

    this.postcodeService.updatePostcode(this.postcode, lat, lng)
      .subscribe({
        next: (data: PostcodeUpdateResult) => {
          this.result = data;
          this.success = 'Postcode coordinates updated successfully';
          this.loading = false;
        },
        error: (err: HttpErrorResponse) => {
          this.error = err.error?.message || 'An error occurred while updating postcode';
          this.loading = false;
        }
      });
  }

  resetForm(): void {
    this.postcode = '';
    this.latitude = '';
    this.longitude = '';
    this.result = null;
    this.error = '';
    this.success = '';
  }
} 