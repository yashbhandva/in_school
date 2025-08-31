# Free Deployment Guide for Eazy School Application

## Option 1: Railway (Recommended - Free $5 credit monthly)

### Steps:
1. **Sign up**: Go to [railway.app](https://railway.app) and sign up with GitHub
2. **Create new project**: Click "New Project" → "Deploy from GitHub repo"
3. **Connect repository**: Connect your GitHub account and select this repository
4. **Add MySQL database**: 
   - Click "New" → "Database" → "Add MySQL"
   - Railway will provide connection details automatically
5. **Set environment variables**:
   - `SPRING_PROFILES_ACTIVE=prod`
   - `DATABASE_URL` (auto-provided by Railway MySQL)
   - `DB_USERNAME` (auto-provided)
   - `DB_PASSWORD` (auto-provided)
6. **Deploy**: Railway will automatically build and deploy your app

### Cost: FREE (with $5 monthly credit)

---

## Option 2: Render (Free tier with sleep mode)

### Steps:
1. **Sign up**: Go to [render.com](https://render.com) and sign up with GitHub
2. **Create Web Service**: 
   - Click "New" → "Web Service"
   - Connect your GitHub repository
3. **Configure**:
   - Build Command: `./mvnw clean package -DskipTests`
   - Start Command: `java -jar target/in-0.0.1-SNAPSHOT.jar`
4. **Add PostgreSQL database** (free):
   - Create new PostgreSQL database on Render
   - Update application-prod.properties to use PostgreSQL
5. **Set environment variables**:
   - `SPRING_PROFILES_ACTIVE=prod`
   - `DATABASE_URL` (from Render PostgreSQL)
6. **Deploy**: Render will build and deploy automatically

### Cost: FREE (with limitations - sleeps after 15 min inactivity)

---

## Option 3: Local Docker Deployment

### Steps:
1. **Install Docker**: Download from [docker.com](https://docker.com)
2. **Build image**: 
   ```bash
   docker build -t eazy-school .
   ```
3. **Run with Docker Compose**:
   ```bash
   docker-compose up -d
   ```

---

## Database Options for Free Deployment:

### For Railway:
- Use Railway's MySQL (included in free tier)

### For Render:
- Use Render's PostgreSQL (free tier available)
- Update dependencies in pom.xml:
  ```xml
  <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <scope>runtime</scope>
  </dependency>
  ```

### For other platforms:
- **PlanetScale** (free MySQL)
- **Supabase** (free PostgreSQL)
- **MongoDB Atlas** (free MongoDB)

---

## Pre-deployment Checklist:

✅ JAR file built successfully
✅ Dockerfile created
✅ Production configuration ready
✅ Railway/Render config files created
✅ Environment variables documented

## Next Steps:

1. Choose your preferred platform (Railway recommended)
2. Push your code to GitHub
3. Follow the platform-specific steps above
4. Set up the database
5. Configure environment variables
6. Deploy!

## Important Notes:

- **Security**: Change JWT_SECRET_KEY and DEFAULT_SECRET_VALUE in production
- **Database**: The app will create tables automatically on first run
- **Port**: The app will use PORT environment variable (required by most platforms)
- **Health Check**: Available at `/actuator/health`

## Support:

If you encounter issues:
1. Check the platform's logs
2. Verify environment variables are set correctly
3. Ensure database connection is working
4. Check if the health endpoint responds