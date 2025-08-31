# Deploy to Render - Step by Step

## 🚀 Quick Deployment Steps:

### 1. Push to GitHub
```bash
git add .
git commit -m "Ready for Render deployment"
git push origin main
```

### 2. Create Render Account
- Go to [render.com](https://render.com)
- Sign up with GitHub

### 3. Create PostgreSQL Database
- Click "New" → "PostgreSQL"
- Name: `eazy-school-db`
- Keep free tier selected
- Click "Create Database"
- **Save the connection details** (you'll need them)

### 4. Create Web Service
- Click "New" → "Web Service"
- Connect your GitHub repository
- Select this project

### 5. Configure Web Service
- **Name**: `eazy-school`
- **Build Command**: `./mvnw clean package -DskipTests`
- **Start Command**: `java -jar target/in-0.0.1-SNAPSHOT.jar`

### 6. Set Environment Variables
Click "Environment" and add:
- `SPRING_PROFILES_ACTIVE` = `prod`
- `DATABASE_URL` = (copy from your PostgreSQL database)
- `PORT` = `8080`

### 7. Deploy
- Click "Create Web Service"
- Wait for deployment (5-10 minutes)
- Your app will be live at: `https://your-app-name.onrender.com`

## ✅ Your app is now configured for:
- PostgreSQL database (free tier)
- Automatic SSL certificate
- Health checks at `/actuator/health`
- Production-ready settings

## 📝 Important Notes:
- Free tier sleeps after 15 minutes of inactivity
- First request after sleep takes ~30 seconds to wake up
- Database persists even when app sleeps